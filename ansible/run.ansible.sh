#!/bin/bash

echo "Starting ansible..."

ANSIBLE_HOST_KEY_CHECKING=false ansible-playbook -i ../terraform/hosts --private-key ../terraform/key/rach_key rach-playbook.yml -v