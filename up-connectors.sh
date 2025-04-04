#!/bin/bash

curl -X POST -H "Content-Type:application/json" localhost:8083/connectors -d @postgresql-source.json

curl -X POST -H "Content-Type:application/json" localhost:8083/connectors -d @elasticsearch-sink.json

curl -X GET -H "Content-Type:application/json" localhost:8083/connectors

echo ""
echo "INFO: connectors up"
