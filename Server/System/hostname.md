



* 변경 방법
```sh
#!/bin/bash

HOST_M_NAME="bastion"

# /etc/cloud/cloud.cfg
sudo sed -i "s/preserve_hostname:\\s*true/preserve_hostname:\\s*false/" /etc/cloud/cloud.cfg

# Change Hostname
sudo hostnamectl set-hostname $HOST_M_NAME

CURRENT_HOSTNAME=$(cat /etc/hostname)

echo $CURRENT_HOSTNAME

if [ "$CURRENT_HOSTNAME" != "$HOST_M_NAME" ]; then
    # 호스트네임을 새로 설정
    echo $HOST_M_NAME | sudo tee /etc/hostname > /dev/null
    echo "Hostname updated to $HOST_M_NAME"
else
    echo "Hostname is already set to $HOST_M_NAME"
fi

sleep 3

sudo reboot

```