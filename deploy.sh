chmod 400 $AWS_PEM
ssh -o "StrictHostKeyChecking=no" -i $AWS_PEM $username@$IPaddress "rm -rf ws-bank && mkdir ws-bank"
scp -r -o "StrictHostKeyChecking=no" -i $AWS_PEM * $username@$IPaddress:~/ws-bank
ssh -o "StrictHostKeyChecking=no" -i $AWS_PEM $username@$IPaddress "bash" < ./run.sh