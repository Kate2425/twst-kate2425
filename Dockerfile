# ベースイメージを指定
FROM maven:4.0.0-openjdk-21-slim AS build 

# 作業ディレクトリを設定
WORKDIR /myapp

# Maven Wrapperのセットアップ
COPY .mvn .mvn

# Mavenプロジェクトをビルド
COPY pom.xml .
RUN mvn -B dependency:go-offline

# アプリケーションのビルド
COPY src src
RUN mvn -B package -DskipTests

# 本番用の軽量なJREベースイメージを使用
FROM openjdk:21-jdk-slim

# アプリケーションのJARファイルをコピー
COPY --from=build /app/target/*.jar /app/app.jar

# アプリケーションの実行
CMD ["java", "-jar", "/app/app.jar"]