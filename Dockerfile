# ベースイメージを指定
FROM maven:3.8.3-openjdk-17-slim AS build

# 作業ディレクトリを設定
WORKDIR /app

# Maven をインストール
COPY .mvn .mvn

# 依存関係を事前にダウンロード（ビルド時間短縮のため）
COPY pom.xml .
RUN mvn dependency:go-offline -B

# アプリケーションのビルド
COPY . /app
RUN mvn -B  clean package -DskipTests

 # ホットリロードを有効にする環境変数を設定
 ENV JAVA_OPTS="-Dspring.devtools.restart.enabled=true -Dspring.devtools.livereload.enabled=true"
 
 # アプリケーションの起動
 ENTRYPOINT ["java", "-jar", "/app/target/twst-0.0.1-SNAPSHOT.jar", "--spring.devtools.restart.enabled=true"]