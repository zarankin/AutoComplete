# AutoComplete
Spring boot application for auto complete words, the vocabulary taken from given text file when every word in different row (every word in the file is made up of lowcase letters only).
Using maven and docker file, java 16.

to run with docker:
docker pull alonazarenkin/auto_complete:autocomplete
docker run -p 9090:9090 alonazarenkin/auto_complete:auto_complete 
