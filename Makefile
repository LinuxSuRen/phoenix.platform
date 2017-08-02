build:
  cd src/main/docker && docker build -t surenpi/phoenix.platform
  
push:
  docker push surenpi/phoenix.platform

.PHONY: build push