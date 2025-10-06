package com.example.twst.dao;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import com.example.twst.form.CardForm;
import com.example.twst.domain.model.BuffDebuffGroupingEnum;
import com.example.twst.domain.model.TableEnum;
import com.example.twst.domain.model.CharacterEnum;
import com.example.twst.domain.model.MagicGroupingEnum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@Transactional
public class CardDaoJdbcImplTest {
        @Autowired
        private CardDaoJdbcImpl target;

        @Test
        @DisplayName("insertOne")
        void insertOne() throws Exception {
                // Arrange 
                CardForm cardForm = new CardForm();
                cardForm.setTableName(TableEnum.BEANS_CAMO);
                cardForm.setName(CharacterEnum.LEONA);
                cardForm.setRare("R");
                cardForm.setType("DEFENCE");
                cardForm.setBuddy1(CharacterEnum.AZUL);
                cardForm.setBuddy2(CharacterEnum.KALIM);
                cardForm.setBuddy3(CharacterEnum.HYPHEN);
                cardForm.setMagic1(MagicGroupingEnum.FIRE_SHOT);
                cardForm.setMagic2(MagicGroupingEnum.RULER_OF_ALL_EVIL3);
                cardForm.setMagic3(MagicGroupingEnum.HYPHEN);
                cardForm.setDuo(CharacterEnum.HYPHEN);
                cardForm.setMinHp(BigDecimal.valueOf(1111));
                cardForm.setMinAtk(BigDecimal.valueOf(2222));
                cardForm.setMaxHp(BigDecimal.valueOf(3333));
                cardForm.setMaxAtk(BigDecimal.valueOf(4444));
                cardForm.setValidFlg(true);
                cardForm.setBuddy1Grouping("2");
                cardForm.setBuddy2Grouping("5");
                cardForm.setBuddy3Grouping("0");
                cardForm.setMagic1BuffdebuffGrouping(BuffDebuffGroupingEnum.ATK_DOWN_MINIMUM_ENEMY_1T);
                cardForm.setMagic2BuffdebuffGrouping(BuffDebuffGroupingEnum.HYPHEN);
                cardForm.setMagic3BuffdebuffGrouping(BuffDebuffGroupingEnum.HP_RECOVER_MEDIUM);

                // Act 
                int count = target.insertOne(cardForm);

                // Assert
                assertThat(count, is(1));
        }

        @Test
        @DisplayName("updateOne")
        void updateOne() throws Exception {
                // Arrange
                CardForm cardForm = new CardForm();
                cardForm.setTableName(TableEnum.GALA_COUTURE);
                cardForm.setRare("SR");
                cardForm.setType("ATTACK");
                cardForm.setName(CharacterEnum.JACK);
                cardForm.setBuddy1(CharacterEnum.JAMIL);
                cardForm.setBuddy2(CharacterEnum.VIL);
                cardForm.setBuddy3(CharacterEnum.HYPHEN);
                cardForm.setMagic1(MagicGroupingEnum.AQUA_WAVE);
                cardForm.setMagic2(MagicGroupingEnum.LEAF_SHOT2);
                cardForm.setMagic3(MagicGroupingEnum.HYPHEN);
                cardForm.setMagic1BuffdebuffGrouping(BuffDebuffGroupingEnum.ATK_DOWN_MEDIUM_ENEMY_1T);
                cardForm.setMagic2BuffdebuffGrouping(BuffDebuffGroupingEnum.DAMAGE_DOWN_MEDIUM_ENEMY_1T);
                cardForm.setMagic3BuffdebuffGrouping(BuffDebuffGroupingEnum.HYPHEN);
                cardForm.setDuo(CharacterEnum.HYPHEN);
                cardForm.setMinHp(BigDecimal.valueOf(0123));
                cardForm.setMinAtk(BigDecimal.valueOf(1234));
                cardForm.setMaxHp(BigDecimal.valueOf(2345));
                cardForm.setMaxAtk(BigDecimal.valueOf(3456));

                // Act
                int count = target.updateOne(cardForm);

                // Assert
                assertThat(count, is(1));
        }

        @Test
        @DisplayName("deleteOne")
        void deleteOne() throws Exception {
                // Arrange
                CardForm cardForm = new CardForm();
                cardForm.setTableName(TableEnum.ROLL_PLAYING_BRIDEGROOM);
                cardForm.setRare("R");
                cardForm.setType("ATTACK");
                cardForm.setName(CharacterEnum.ROOK);
                cardForm.setBuddy1(CharacterEnum.ORTHO);
                cardForm.setBuddy2(CharacterEnum.HYPHEN);
                cardForm.setBuddy3(CharacterEnum.HYPHEN);
                cardForm.setMagic1(MagicGroupingEnum.FIRE_SHOT);
                cardForm.setMagic2(MagicGroupingEnum.FIRE_SHOT2);
                cardForm.setMagic3(MagicGroupingEnum.HYPHEN);
                cardForm.setMagic1BuffdebuffGrouping(BuffDebuffGroupingEnum.DAMAGE_UP_SMALL_SELF_1T);
                cardForm.setMagic2BuffdebuffGrouping(BuffDebuffGroupingEnum.ATK_DOWN_MINIMUM_ENEMY_1T);
                cardForm.setMagic3BuffdebuffGrouping(BuffDebuffGroupingEnum.HYPHEN);
                cardForm.setDuo(CharacterEnum.HYPHEN);
                cardForm.setMinHp(BigDecimal.valueOf(0123));
                cardForm.setMinAtk(BigDecimal.valueOf(1234));
                cardForm.setMaxHp(BigDecimal.valueOf(2345));
                cardForm.setMaxAtk(BigDecimal.valueOf(3456));

                // Act
                int count = target.updateOne(cardForm);

                // Assert
                assertThat(count, is(1));
        }

        @Test
        @DisplayName("csvOut")
        void csvOut() {
                // Act
                target.csvOut();
        }

}
