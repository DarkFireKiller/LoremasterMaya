# LoremasterMaya
### A Discord bot for the AQW/AQ3D Wiki server
-----

This project is built with the openjdk-15 SDK

## Steps
* Download the latest JAR from releases
* Run the jarfile. Takes location of config files as arg
    * `java -jar LoremasterMaya.jar /path/to/config/`
    * Config should be in `/path/to/config/config.json`
 
## Config file

#### config.json
```json
{
  "token": "bot_token1234567890",
  "prefix": "prefix",
  "admins": [
      "UserSnowflakeID", "UserSnowflakeID"
  ]
}
```

### Testing

Set `testing = true` in `Main` to read from `configTest.json` instead. Useful for testing on secondary Bot user.

Doing this will also use the directory defined at `Settings.defaultFolder` for config file instead of providing it as an arg.
