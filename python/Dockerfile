FROM python:latest

WORKDIR /app

COPY script.py stores.json ./

RUN apt-get update -y && pip install psycopg2

CMD [ "python", "./script.py"]