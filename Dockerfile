FROM ruby:3-slim
COPY http_server.rb /http_server.rb
RUN chmod ugo+r /http_server.rb && \
  apt-get update && apt-get install -y curl && \
  rm -rf /var/lib/apt/lists/*
WORKDIR /
ENTRYPOINT ["ruby","http_server.rb"]
