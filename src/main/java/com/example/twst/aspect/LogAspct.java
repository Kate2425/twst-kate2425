package com.example.twst.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspct {

    /**
     * Controllerクラスのログ出力用アスペクト
     */
    @Around("execution(* *..*.*Controller.*(..))")
    public Object startLog(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("メソッド開始： " + jp.getSignature());

        try {
            // メソッド実行
            Object result = jp.proceed();
            System.out.println("メソッド終了： " + jp.getSignature());
            return result;

        } catch (Exception e) {
            System.out.println("メソッド異常終了： " + jp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Daoクラスのログ出力用アスペクト
     */
    @Around("execution(* *..*.*Dao*.*(..))")
    public Object daoLog(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("メソッド開始： " + jp.getSignature());
        try {
            Object result = jp.proceed();
            System.out.println("メソッド終了： " + jp.getSignature());
            return result;
        } catch (Exception e) {
            System.out.println("メソッド異常終了： " + jp.getSignature());
            e.printStackTrace();
            throw e;
        }
    }

}
