# Port forward into the contour pod
CONTOUR_POD=$(kubectl -n projectcontour get pod -l app=contour -o name | head -1)
# Do the port forward to that pod
kubectl -n projectcontour port-forward $CONTOUR_POD 6060 &
sleep 2
curl localhost:6060/debug/dag > contour-dag.dot
dot contour-dag.dot -T png > contour-dag.png
kill %1