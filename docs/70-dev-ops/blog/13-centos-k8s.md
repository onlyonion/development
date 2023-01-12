



## kubeadm
kubeadm init --image-repository=registry.aliyuncs.com/google_containers
kubeadm join 192.168.0.115:6443 --token qn3u0x.mff3wqbkkzo1ilvq --discovery-token-ca-cert-hash sha256:03a7a7589da0b3d84bf6ad866f0db04cc05973e55e429a929b4e1dc1ba0eb2e4 

kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml




sudo docker run -d \
  --restart=unless-stopped \
  --name=kuboard \
  -p 80:80/tcp \
  -p 10081:10081/tcp \
  -e KUBOARD_ENDPOINT="http://192.168.0.115:80" \
  -e KUBOARD_AGENT_SERVER_TCP_PORT="10081" \
  -v /root/kuboard-data:/data \
  eipwork/kuboard:v3

admin
Kuboard123



kubectl create -f install-nginx.yml


### kubesphere
port:30880

### kubeboard