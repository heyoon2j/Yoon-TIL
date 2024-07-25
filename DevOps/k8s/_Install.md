# kubectl

```sh
#!/bin/bash

# Download kubectl
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"

# Download kubectl checksum
curl -LO "https://dl.k8s.io/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl.sha256"

# Checking checksum
CHECKSUM_RESULT=$(echo "$(cat kubectl.sha256)  kubectl" | sha256sum --check)

if [[ $CHECKSUM_RESULT == *"kubectl: OK"* ]]; then
    echo "kubectl: OK"
    # Install kubectl
    sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
else
    echo "kubectl: FAILED"
    exit 1
fi
```

---

# minicube
```sh
#!/bin/bash

sudo dnf install -y docker
sudo systemctl start docker
sudo systemctl enable docker
sudo usermod -aG docker $USER
newgrp docker



# Add Installation
sudo dnf install -y conntrack

VERSION="v1.25.0"
curl -LO https://github.com/kubernetes-sigs/cri-tools/releases/download/$VERSION/crictl-$VERSION-linux-amd64.tar.gz
sudo tar zxvf crictl-$VERSION-linux-amd64.tar.gz -C /usr/local/bin
rm -f crictl-$VERSION-linux-amd64.tar.gz




curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64

sudo install minikube-linux-amd64 /usr/local/bin/minikube && rm minikube-linux-amd64

sudo sysctl fs.protected_regular=0
minikube start --driver=docker

```
- conntrack : 네트워크 연결 추적 및 관리 도구로, Kubernetes 클러스터에서 네트워크 연결을 관리하는 데 사용
- crictl : CRI(Container Runtime Interface) 도구로, Kubernetes에서 컨테이너 런타임을 관리하는 데 사용
