# mc-backdoor

> Still in development, most of the features are not implemented yet.

A simple plugin adding backdoors into Spigot Servers. It is "disguised" as SpigotCommonLib to avoid detection. I made this as a POC, please don't use for evil :)

### There are currently 3 backdoors:

1. Sudo allows someone to execute any command as the console or another player. This is useful for bypassing permissions. Sudo also allows for the command to be run on the system as the user running the java process. The sudo command prefix can be configured in `pom.xml`
2. Reverse SSH allows you to connect to the server from a remote machine. This is useful for reverse shells. The reverse ssh URI can be configured in `pom.xml`
3. Startup commands are run on the system when the plugin is enabled. This is useful for creating other backdoors. The startup commands can be configured in `pom.xml`

## Build Instructions

```bash
# build jar with maven
$ mvn package
```
