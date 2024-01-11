# sb3

using jwt

返回样式

```json
{
    "code":"int",
    "data":"obj",
    "msg":"str"
}
```

请求头

Authorization: Bearer [token]

请求头以 "Bearer " 开头是一种规范，用于标识请求中携带的是令牌。这种标准化有助于服务器端更轻松地识别出令牌并进行处理，确保安全性并进行必要的身份验证

## 技术

- 后端：sb3+mysql+redis+mq+jwt
- 前端：vue+vite+ElementPlus

使用redis实现jwt黑名单机制
