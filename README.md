# 获取数据库用户登录token
curl -X POST "http://123.60.137.159:7573/uaa/baseLogin" -H "Content-Type: application/json" -d "{\"username\":\"user_1\", \"password\":\"user_1\"}"


# 获取产品列表
curl -X GET "http://123.60.137.159:7573/product/getProducts" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyXzEiLCJwYXNzd29yZCI6InVzZXJfMSIsInJvbGVOYW1lIjoiVVNFUiIsImlhdCI6MTcyNDQ1NDAwOX0.T-xonOc41N8xMIuE_yabQa1LCoFDbEMRer9y0qzaDUg"

# 添加产品
curl -X GET "http://123.60.137.159:7573/product/addProduct" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyXzEiLCJwYXNzd29yZCI6InVzZXJfMSIsInJvbGVOYW1lIjoiVVNFUiIsImlhdCI6MTcyNDQ1NDM2M30.4ueCUzELVs6KtEoKF083fTy_DtbTwCz46bbN0dCanQE"

# 修改产品
curl -X GET "http://123.60.137.159:7573/product/updateProduct?name=%E6%96%B0%E4%BA%A7%E5%93%81%E5%90%8D%E7%A7%B0&id=1" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyXzEiLCJwYXNzd29yZCI6InVzZXJfMSIsInJvbGVOYW1lIjoiVVNFUiIsImlhdCI6MTcyNDQ1NDM2M30.4ueCUzELVs6KtEoKF083fTy_DtbTwCz46bbN0dCanQE"

# 删除产品
curl -X GET "http://123.60.137.159:7573/product/deleteProduct?id=2" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyXzEiLCJwYXNzd29yZCI6InVzZXJfMSIsInJvbGVOYW1lIjoiVVNFUiIsImlhdCI6MTcyNDQ1NDM2M30.4ueCUzELVs6KtEoKF083fTy_DtbTwCz46bbN0dCanQE"
