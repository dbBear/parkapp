#!/bin/bash

echo building fakeSMTPParkApp

docker stop parkAppSmtp
docker rm parkAppSmtp
docker run --name parkAppSmtp -d -p 6000:25 -v /home/drumb/dockerdata/fakemail/mail:/var/mail ghusta/fakesmtp