FROM openjdk:8-jdk-alpine

# 设置工作目录
WORKDIR /app

# 将应用程序复制到容器中
COPY start/target/start-0.0.1-SNAPSHOT.jar /app

# 暴露端口
EXPOSE 8080

# 启动应用程序
CMD ["java", "-jar", "start-0.0.1-SNAPSHOT.jar"]