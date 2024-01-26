#!/bin/bash

sed -e "s/localhost:8080/fastburger-api-$(minikube ip | tr '.' '-').nip.io/" scripts/load_products_table_via_svc.sh > scripts/load_products_table_via_ingress.sh
