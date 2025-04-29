# ベースイメージとしてOpenJDK 17のslimバージョンを使用
FROM openjdk:21-jdk-slim

# 作業ディレクトリを設定
WORKDIR /app

# 作成したアプリケーションのJarファイルをイメージ内にコピー
COPY target/twst-0.0.1-SNAPSHOT.jar /app.jar

# アプリケーションを実行
ENTRYPOINT ["java", "-jar", "/app.jar"]