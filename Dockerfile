# ベースイメージを指定
FROM openjdk:17-jdk-alpine

# Maven をインストール
RUN apk add --no-cache maven

# 作業ディレクトリを設定
WORKDIR /myapp

# 依存関係を事前にダウンロード（ビルド時間短縮のため）
COPY pom.xml .
RUN mvn dependency:go-offline -B

# プロジェクトのソースコードをコピー
COPY /src .

# アプリケーションのビルド
COPY ./src src
RUN mvn -B  clean package -DskipTests

 # ホットリロードを有効にする環境変数を設定
 ENV JAVA_OPTS="-Dspring.devtools.restart.enabled=true -Dspring.devtools.livereload.enabled=true"
 
 # アプリケーションの起動
 ENTRYPOINT ["java", "-jar", "/myapp/target/twst-0.0.1-SNAPSHOT.jar", "--spring.devtools.restart.enabled=true"]