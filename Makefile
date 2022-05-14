.DEFAULT_GOAL := build

clean:
	./gradlew clean

build: clean
	./gradlew build -x test -x checkstyleMain -x checkstyleTest

lint:
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

docker-build:
	docker build --build-arg BUILD_VERSION=0.0.1-SNAPSHOT -t motemere/testproject-endproxy:latest -t motemere/testproject-endproxy:0.0.1-SNAPSHOT .
