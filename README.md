# java-main Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

Если завел jpa папку и работу с бд то уже не получится запустить кваркус без бд - в примерах на выбор либо локальная бд либо из докера

// start quarkus with all clean and install java deps
mvn clean install

// start quarkus without tests
mvn clean install -Dmaven.test.skip=true

// start quarkus from root
mvn compile quarkus:dev

// 2nd way start quarkus from root of the project
java -jar portal-application/target/quarkus-app/quarkus-run.jar

// чтобы включить бд из докера
docker compose up

// чтобы подключить бд локально
brew services start postgresql (подробности /Projects/react-main/main/development_docs/db/postgres/postgres.md)