# MinecartMold

This repository exists as a public code archive of the Minecart Rapid Transit server's April Fools plugin for 2022. Commit and issue history are not available because chiefbozx (whose contributions to this plugin were instrumental to its success) used a personal account to sign commits/issues and does not want this leaked.

## How it works

Every x minutes, the plugin will ban a random letter from chat messages up until the configured limit. Once this limit is reached, a letter will also be unbanned between bans, so as to keep the number of banned letters at the same level. All banned letters and characters above Unicode point 127 (`0x7F`) will be dropped from messages.