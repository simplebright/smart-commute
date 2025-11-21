local key = KEYS[1]
local limit = tonumber(ARGV[1])
local expire = tonumber(ARGV[2])

local current = redis.call('incr', key)
if current == 1 then
  redis.call('expire', key, expire)
end

if current > limit then
  return 0
end

return 1