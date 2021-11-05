# Port forward into the contour pod
PROMETHEUS_POD=$(kubectl -n projectcontour-monitoring get pod -l app=prometheus -l component=server -o name | head -1)
# Do the port forward to that pod
kubectl -n projectcontour-monitoring port-forward $PROMETHEUS_POD 9090 &
sleep 2
open http://localhost:9090