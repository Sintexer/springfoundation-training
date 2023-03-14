# subtask 3 - Spring Actuator

- Switched to springFlux to apply to the requirement 'Expose Default Spring Actuator by configuring SecurityWebFilterChain bean. ', 
since SecurityWebFilterChain is a webflux class.
- Added security and user `admin` with password `password`
- All actuator endpoints, except health, are only accessible after authorization
- Added new endpoint "random", which returns randomBoolean and randomInt.