cd ws-bank
sudo docker container stop ws-bank
sudo docker container rm ws-bank
mvn package
sudo docker image build -t willy/ws-bank ./
sudo docker container run -d --name ws-bank -it --network="host" willy/ws-bank