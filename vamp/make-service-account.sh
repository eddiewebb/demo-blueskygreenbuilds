svcuser=${1}

kubectl create serviceaccount ${svcuser}

kubectl create clusterrolebinding ${svcuser} --clusterrole=cluster-admin --serviceaccount=default:${svcuser}


kubectl get secret $(kubectl get serviceaccounts ${svcuser} -o go-template='{{(index .secrets 0).name}}') -o yaml

echo ""
echo "Add the token value above to Env Vars, and add ca.crt to envvars or config (public on API)"