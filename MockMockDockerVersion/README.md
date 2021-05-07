# Docker for mockmock

originals project is avalaible here: https://github.com/tweakers/MockMock

## Overview

Containerized version of MockMock

## Requirements

* A machine running docker

## Installation / Setup 

To launch the application we will ues a docker container named `res-mockmock` that will give us the possibility to use MockMock without installing it on our local machine.

To install run the following command:

```bash
docker build -t res-mockmock .
```

When the image is built run the following command to start it:

```bash
docker run -p 2525:2525 -p 8282:8282 res-mockmock
```

With this, the smtp server will be avalaible on port 2525 and web panel on port 8282 

