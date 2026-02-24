# springai-opensearch-vector-search-demo
# springai-opensearch-vector-search-demo

## to download certificate
```sh
openssl s_client -showcerts -connect localhost:9200 </dev/null | sed -n -e '/-.BEGIN/,/-.END/ p' > certifs.cer


```

## to import certificate

```sh

 keytool -import -alias opensearchcert -file "/Users/yenealemsettegne/cert/certifs.cer" -keystore "/Library/Java/JavaVirtualMachines/graalvm-25.jdk/Contents/Home/lib/security/cacerts"
```

## request

```http request
# https://localhost:9200 -ku admin:strongPass123#
```
## output
```curl
C:\Users\Pritesh>curl https://localhost:9200 -ku admin:strongPass123#
{
  "name" : "fac6b4f245a1",
  "cluster_name" : "docker-cluster",
  "cluster_uuid" : "hVLYTpDUTFq4QJjh_wVxFw",
  "version" : {
    "distribution" : "opensearch",
    "number" : "2.17.0",
    "build_type" : "tar",
    "build_hash" : "8586481dc99b1740ca3c7c966aee15ad0fc7b412",
    "build_date" : "2024-09-13T01:04:14.707418737Z",
    "build_snapshot" : false,
    "lucene_version" : "9.11.1",
    "minimum_wire_compatibility_version" : "7.10.0",
    "minimum_index_compatibility_version" : "7.0.0"
  },
  "tagline" : "The OpenSearch Project: https://opensearch.org/"
}

```