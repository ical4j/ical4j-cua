SHELL:=/bin/bash
include .env

NEXT_VERSION=$(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))
CHANGE_JUSTIFICATION=$(wordlist 2,$(words $(MAKECMDGOALS)),$(MAKECMDGOALS))

.PHONY: all gradlew clean check build changelog currentVersion markNextVersion tag push run \
	verify release publish

all: check

gradlew:
	./gradlew wrapper --gradle-version=$(GRADLE_VERSION) --distribution-type=bin

clean:
	./gradlew clean

check:
	./gradlew check

test:
	./gradlew test

build:
	./gradlew build

installDist:
	./gradlew installDist

dist:
	./gradlew distZip

uploadDist: dist
	AWS_PROFILE=$(AWS_PROFILE) aws s3 sync --endpoint=$(AWS_ENDPOINT) --acl public-read --exclude "*" --include "*.zip" \
		build/distributions $(DIST_BUCKET)/releases

changelog:
	git log "$(CHANGELOG_START_TAG)...$(CHANGELOG_END_TAG)" \
    	--pretty=format:'* %s [View commit](http://github.com/ical4j/ical4j-command/commit/%H)' --reverse | grep -v Merge

currentVersion:
	./gradlew -q currentVersion

markNextVersion:
	./gradlew markNextVersion -Prelease.version=$(NEXT_VERSION)

install:
	./gradlew publishToMavenLocal

verify:
	./gradlew verify

release: verify
	./gradlew release

publish:
	./gradlew publish
