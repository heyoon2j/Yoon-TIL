






```
# Node & Pod

kubectl get pod                                 # Print Pod information
kubectl get pod -o wide                         # Output Option : wide
kubectl get pods -o=custom-columns=NAME:.metadata.name,IP:.status.podIP,STATUS:.status.phase,NODE:.spec.nodeName
kubectl get pods speaker-vnc2k -o yaml - n metallb-system
kubectl get pods --all-namespace                # Print all Pod information




kubectl create -f <YAML File>                   # Create Object





kubectl run <Pod Nme> --image=nginx             # Run Pod

kubectl delete pod <Pod Name>                   # Delete Pod





kubectl api-resource                            # Print resource list




```






```
# Object


kubectl create deployment dpy-nginx --image=nginx

kubectl create -f ~/dpy-template.yaml


kubectl apply -f ~/dpy-template.yaml


## History
$ kubectl apply -f ~/dpy-template.yaml --record     # 배포 History 기록
$ kubectl rollout history depoyment dpy-tempate     # 배포 History 출력


$ kubectl describe deployment rollout-nginx         # 상태 출력


$ kubectl delete -f ~/dpy-template.yaml

```
* ad-hoc(일회적으로 사용)으로 Object를 생성하는 경우, Create
* 지속적으로 변경이 생길 가능성이 있는 복잡한 Object를 생성하는 경우, Apply





```
kubectl exec -it nginx-pod -- /bin/bash



```


```
# Node Management


$ kubectl get nodes --kubeconfig admin.conf       # Print Node inforamtion


$ kubectl cordon <node_name>

$ kubectl drain <node_name>
```


```
# Service
$ kubectl get services

$ kubectl apply -f ~/nodport.yaml


$ kubectl expose deplyment np-pods --type=NodePort --name=np-svc-v2 --prot=80

$ kubectl delete services np-svc

```
* xpose로도 생성 가능하나 NodePort를 지정할 수 없다. 임의로 포트가 지정됨 (30000 ~ 32767)