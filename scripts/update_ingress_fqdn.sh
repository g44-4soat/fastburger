#!/bin/bash

sed -e "s/{{FQDN}}/fastburger-api-$(minikube ip | tr '.' '-').nip.io/" scripts/ingress.template > manifests/ingress.yaml
