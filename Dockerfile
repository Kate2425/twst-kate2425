FROM openjdk:11-slim

# 作業ディレクトリを設定
WORKDIR /app

# Javaファイルをコピー
COPY TwstApplication.java /app

# Javaファイルをコンパイル
RUN javac TwstApplication.java

# ポート8080を公開
EXPOSE 8080

# アプリケーションを実行
CMD ["java", "TwstApplication"]
