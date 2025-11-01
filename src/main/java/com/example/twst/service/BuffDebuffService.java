package com.example.twst.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.example.twst.domain.model.BuffDebuffGroupingEnum;

@Service("BuffDebuffService")
public class BuffDebuffService {

    /**
     * バフデバフ倍率を取得する.
     * @param buff
     * @param magicLevel
     * @return buffDebuffPower
     */
    public BigDecimal getBuffDebuffPower(BuffDebuffGroupingEnum buff, String magicName, int magicLevel) {
        BigDecimal buffDebuffPower = BigDecimal.ZERO;
        String buffDebuff = buff.getType();
        if (buffDebuff.startsWith("ATK")) {
            buffDebuffPower = buffDebuffPower.add(getAttackUpOrDown(buff, magicLevel));
        } else if (buffDebuff.startsWith("属性ダメージ")) {
            buffDebuffPower = buffDebuffPower.add(getMagicTypeDamageUpOrDown(buff, magicName, magicLevel));
        } else if (buffDebuff.startsWith("被ダメージ")) {
            buffDebuffPower = buffDebuffPower.add(getDamageUpOrDown(buff, magicLevel));
        } else if (buffDebuff.startsWith("ダメージ")) {
            buffDebuffPower = buffDebuffPower.add(getDamageUpOrDown(buff, magicLevel));
        } else {
            buffDebuffPower = buffDebuffPower.add(BigDecimal.valueOf(-1));
        }
        return buffDebuffPower;
    }

    /**
     * ATKUP/ATKDOWNの倍率を取得する.
     * @param buff
     * @param magicLevel 
     * @return attackPower
     */
    private BigDecimal getAttackUpOrDown(BuffDebuffGroupingEnum buff, int magicLevel) {
        String strength = buff.getStrength();
        BigDecimal attackPower = BigDecimal.ZERO;

        switch (strength) {
            case "極小" -> {
                attackPower = attackPower.add(BigDecimal.valueOf(0.05));
                attackPower = attackPower.add(BigDecimal.valueOf(0.005).multiply(BigDecimal.valueOf(magicLevel)));
            }
            case "小" -> {
                attackPower = attackPower.add(BigDecimal.valueOf(0.1));
                attackPower = attackPower.add(BigDecimal.valueOf(0.01).multiply(BigDecimal.valueOf(magicLevel)));
            }
            case "中" -> {
                attackPower = attackPower.add(BigDecimal.valueOf(0.2));
                attackPower = attackPower.add(BigDecimal.valueOf(0.015).multiply(BigDecimal.valueOf(magicLevel)));
            }
            case "大" -> {
                attackPower = attackPower.add(BigDecimal.valueOf(0.5));
            }
        }
        return attackPower;
    }

    /**
     * 属性ダメージUP/DOWNの倍率を取得する.
     * @param buff
     * @param magicLevel
     * @return attackPower
     */
    private BigDecimal getMagicTypeDamageUpOrDown(BuffDebuffGroupingEnum buff, String magicName, int magicLevel) {
        String strength = buff.getStrength();
        BigDecimal attackPower = BigDecimal.ZERO;
        String buffMagicType = buff.getViewName().substring(buff.getViewName().indexOf("属性ダメージ") - 1, 3);
        String magicType = magicName.substring(magicName.indexOf("属性ダメージ") - 1,
                magicName.indexOf("属性ダメージ") - 1 + 3);

        if (buffMagicType.equals(magicType)) {
            switch (strength) {
                case "小" -> {
                    attackPower = attackPower.add(BigDecimal.valueOf(0.03));
                    attackPower = attackPower.add(BigDecimal.valueOf(0.003).multiply(BigDecimal.valueOf(magicLevel)));
                }
                case "中" -> {
                    attackPower = attackPower.add(BigDecimal.valueOf(0.06));
                    attackPower = attackPower.add(BigDecimal.valueOf(0.0045).multiply(BigDecimal.valueOf(magicLevel)));
                }
                case "大" -> attackPower = attackPower.add(BigDecimal.valueOf(0.15));
                //FIXME
                case "極大" -> attackPower = attackPower.add(BigDecimal.valueOf(0.2));
            }
        }
        return attackPower;
    }

    /**
     * ダメージUP/DOWNの倍率を取得する.
     * @param buff
     * @param magicLevel
     * @return attackPower
     */
    private BigDecimal getDamageUpOrDown(BuffDebuffGroupingEnum buff, int magicLevel) {
        String strength = buff.getStrength();
        BigDecimal attackPower = BigDecimal.ZERO;

        switch (strength) {
            case "極小" -> {
                //FIXME
                attackPower = attackPower.add(BigDecimal.valueOf(0.0125));
                attackPower = attackPower.add(BigDecimal.valueOf(0.00125).multiply(BigDecimal.valueOf(magicLevel)));
            }
            case "小" -> {
                attackPower = attackPower.add(BigDecimal.valueOf(0.025));
                attackPower = attackPower.add(BigDecimal.valueOf(0.0025).multiply(BigDecimal.valueOf(magicLevel)));
            }
            case "中" -> {
                attackPower = attackPower.add(BigDecimal.valueOf(0.05));
                attackPower = attackPower.add(BigDecimal.valueOf(0.00375).multiply(BigDecimal.valueOf(magicLevel)));
            }
            case "大" -> attackPower = attackPower.add(BigDecimal.valueOf(0.125));
        }
        return attackPower;
    }

    /**
     * HP回復.
     * @param buff
     * @param magicLevel
     * @param originAtk
     * @return hpRecover
     */
    public BigDecimal recoverHp(BuffDebuffGroupingEnum buff, int magicLevel, BigDecimal originAtk) {
        String strength = buff.getStrength();
        BigDecimal hpRecover = BigDecimal.ZERO;

        switch (strength) {
            case "極小" -> {
                //FIXME
                hpRecover = hpRecover.add(
                        BigDecimal.valueOf(0.5).add(BigDecimal.valueOf(0.01).multiply(BigDecimal.valueOf(magicLevel))));
                hpRecover = hpRecover.multiply(originAtk).setScale(0, RoundingMode.HALF_DOWN);
            }
            case "小" -> {
                hpRecover = hpRecover.add(
                        BigDecimal.valueOf(0.9).add(BigDecimal.valueOf(0.02).multiply(BigDecimal.valueOf(magicLevel))));
                hpRecover = hpRecover.multiply(originAtk).setScale(0, RoundingMode.HALF_DOWN);
            }
            case "中" -> {
                hpRecover = hpRecover.add(
                        BigDecimal.valueOf(1.3).add(BigDecimal.valueOf(0.04).multiply(BigDecimal.valueOf(magicLevel))));
                hpRecover = hpRecover.multiply(originAtk).setScale(0, RoundingMode.HALF_DOWN);
            }
        }
        return hpRecover;
    }

    /**
     * HP継続回復.
     * @param buff
     * @param magicLevel
     * @param originHp
     * @return hpContinuousRecover
     * 
     */
    public BigDecimal hpContinuousRecover(BuffDebuffGroupingEnum buff, int magicLevel, BigDecimal originHp) {
        String strength = buff.getStrength();
        BigDecimal hpContinuousRecover = BigDecimal.ZERO;
        switch (strength) {
            case "極小" -> {
                //FIXME
                hpContinuousRecover = hpContinuousRecover.add(BigDecimal.valueOf(0.05)
                        .add(BigDecimal.valueOf(0.005).multiply(BigDecimal.valueOf(magicLevel))));
                hpContinuousRecover = hpContinuousRecover.multiply(originHp).setScale(0, RoundingMode.HALF_DOWN);
            }
            case "小" -> {
                hpContinuousRecover = hpContinuousRecover.add(BigDecimal.valueOf(0.1)
                        .add(BigDecimal.valueOf(0.005).multiply(BigDecimal.valueOf(magicLevel))));
                hpContinuousRecover = hpContinuousRecover.multiply(originHp).setScale(0, RoundingMode.HALF_DOWN);
            }
            case "中" -> {
                hpContinuousRecover = hpContinuousRecover.add(BigDecimal.valueOf(0.2)
                        .add(BigDecimal.valueOf(0.005).multiply(BigDecimal.valueOf(magicLevel))));
                hpContinuousRecover = hpContinuousRecover.multiply(originHp).setScale(0, RoundingMode.HALF_DOWN);
            }
        }
        return hpContinuousRecover;
    }

    /**
     * 回避判定.
     * @param buff
     * @param evadeRange
     * @param magicLevel
     * @return isEvade
     */
    public boolean isEvade(BuffDebuffGroupingEnum buff, BigDecimal evadeRange, int magicLevel) {
        BigDecimal evadePower = BigDecimal.ZERO;
        String strength = buff.getStrength();

        switch (strength) {
            case "極小" -> {
                evadePower = evadePower.add(BigDecimal.valueOf(0.025))
                        .multiply(BigDecimal.valueOf(magicLevel));
                evadePower = evadePower.add(BigDecimal.valueOf(0.25));
            }
            case "小" -> {
                evadePower = evadePower.add(BigDecimal.valueOf(0.05))
                        .multiply(BigDecimal.valueOf(magicLevel));
                evadePower = evadePower.add(BigDecimal.valueOf(0.5));
            }
            case "中" -> {
                evadePower = evadePower.add(BigDecimal.valueOf(0.075))
                        .multiply(BigDecimal.valueOf(magicLevel));
                evadePower = evadePower.add(BigDecimal.ONE);
            }
        }

        boolean isEvade = (evadePower.compareTo(evadeRange)) != 1;

        return isEvade;
    }

    /**
     *  呪い判定.
     * @param buff
     * @param curseRange
     * @param magicLevel
     * @return isCurse
     */
    public boolean isCurse(BuffDebuffGroupingEnum buff, BigDecimal curseRange, int magicLevel) {
        BigDecimal cursePower = BigDecimal.ZERO;
        String strength = buff.getStrength();

        switch (strength) {
            case "小" -> {
                cursePower = cursePower.add(BigDecimal.valueOf(0.01))
                        .multiply(BigDecimal.valueOf(magicLevel));
                cursePower = cursePower.add(BigDecimal.valueOf(0.1));
            }
            case "中" -> {
                cursePower = cursePower.add(BigDecimal.valueOf(0.015))
                        .multiply(BigDecimal.valueOf(magicLevel));
                cursePower = cursePower.add(BigDecimal.valueOf(0.15));
            }
            case "大" -> {
                cursePower = cursePower.add(BigDecimal.valueOf(0.035))
                        .multiply(BigDecimal.valueOf(magicLevel));
                cursePower = cursePower.add(BigDecimal.valueOf(0.35));
            }
            case "極大" -> {//FIXME
                cursePower = cursePower.add(BigDecimal.valueOf(0.04))
                        .multiply(BigDecimal.valueOf(magicLevel));
                cursePower = cursePower.add(BigDecimal.valueOf(0.4));
            }
        }

        boolean isCurse = (cursePower.compareTo(curseRange)) != 1;
        return isCurse;
    }

    /**
     * 暗闇判定.
     * @param buff
     * @param darknessRange
     * @param magicLevel
     * @return isDarkness
     */
    public boolean isDarkness(BuffDebuffGroupingEnum buff, BigDecimal darknessRange, int magicLevel) {
        BigDecimal darknessPower = BigDecimal.ZERO;
        String strength = buff.getStrength();

        switch (strength) {
            case "小" -> {
                darknessPower = darknessPower.add(BigDecimal.valueOf(0.01))
                        .multiply(BigDecimal.valueOf(magicLevel));
                darknessPower = darknessPower.add(BigDecimal.valueOf(0.1));
            }
            case "中" -> {
                darknessPower = darknessPower.add(BigDecimal.valueOf(0.015))
                        .multiply(BigDecimal.valueOf(magicLevel));
                darknessPower = darknessPower.add(BigDecimal.valueOf(0.15));
            }
            case "大" -> {
                darknessPower = darknessPower.add(BigDecimal.valueOf(0.035))
                        .multiply(BigDecimal.valueOf(magicLevel));
                darknessPower = darknessPower.add(BigDecimal.valueOf(0.35));
            }
            case "極大" -> {//FIXME
                darknessPower = darknessPower.add(BigDecimal.valueOf(0.04))
                        .multiply(BigDecimal.valueOf(magicLevel));
                darknessPower = darknessPower.add(BigDecimal.valueOf(0.4));
            }
        }

        boolean isDarkness = (darknessPower.compareTo(darknessRange)) != 1;
        return isDarkness;
    }

    /**
     * クリティカル判定.
     * @param buff
     * @param criticalRange
     * @param magicLevel
     * @return isCritical
     */
    public boolean isCritical(BuffDebuffGroupingEnum buff, BigDecimal criticalRange, int magicLevel) {
        BigDecimal criticalPower = BigDecimal.ZERO;
        String strength = buff.getStrength();

        switch (strength) {
            case "中" -> {
                criticalPower = criticalPower.add(BigDecimal.valueOf(0.0125))
                        .multiply(BigDecimal.valueOf(magicLevel));
                criticalPower = criticalPower.add(BigDecimal.valueOf(0.125));
            }
            case "極大" -> {//FIXME
                criticalPower = criticalPower.add(BigDecimal.valueOf(0.04))
                        .multiply(BigDecimal.valueOf(magicLevel));
                criticalPower = criticalPower.add(BigDecimal.valueOf(0.4));
            }
        }

        boolean isCritical = (criticalPower.compareTo(criticalRange)) != 1;
        return isCritical;
    }
}
