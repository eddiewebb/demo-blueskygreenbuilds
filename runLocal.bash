#!/bin/bash


circleci config process .circleci/config.yml > local.yml && circleci build --job "Unit Test" -c local.yml