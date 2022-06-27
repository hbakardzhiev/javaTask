# Java Assignment
Assignment description:
  - extract numbers from input file.
  - provide a reference to them. (the currently chosen reference is a row and column index)

# Input
The input I have decided to be a txt file. The following is the content of the input.txt saved on the server:
```
123
1230
1239
8
```

If the user would like to query a file they would have to make a POST request to localhost:8080 and pass as a params the filename as seen in this Postman screenshot:
![2022-06-27-000907_1920x1080_scrot](https://user-images.githubusercontent.com/23415119/175835604-dda8b9dc-6a58-4433-9b64-15a4cd18308d.png)
There are several txt files (e.g. input.txt, 1KB.txt, 10KB.txt, 100KB, 1MB, 10MB) that are for testing on the server you can use.


Note: The file can be of any size and even if it is written on a single line it would be handled properly.


# Output
The output below is generated when the user passes the input.txt file to the Controller.
```yaml
{
  "0": [
    {
      "row": 1,
      "column": 3
    }
  ],
  "1": [
    {
      "row": 0,
      "column": 0
    },
    {
      "row": 1,
      "column": 0
    },
    {
      "row": 2,
      "column": 0
    }
  ],
  "2": [
    {
      "row": 0,
      "column": 1
    },
    {
      "row": 1,
      "column": 1
    },
    {
      "row": 2,
      "column": 1
    }
  ],
  "3": [
    {
      "row": 0,
      "column": 2
    },
    {
      "row": 1,
      "column": 2
    },
    {
      "row": 2,
      "column": 2
    }
  ],
  "8": [
    {
      "row": 3,
      "column": 0
    }
  ],
  "9": [
    {
      "row": 2,
      "column": 3
    }
  ]
}
```
The ideology of the output is that each number present in the user input would be on the 1st level and its occurrences would be on the 2nd level.
This way you would only see numbers that are part of the file and all their exact places in the original document.

# Performance
Speed (all metrics are taken from cold boot):
  - input.txt takes 180ms
  - 1KB.txt takes 11ms
  - 10KB.txt takes 17ms
  - 1000KB.txt takes 6sms
  - 1MB.txt takes 261ms
  - 10MB.txt takes around 1116ms (the output is too big to be transferred over POST. I would have to consider for future improvement chopping output into smaller packets to send successfully.)

Big-O complexity analysis:
  - creation of variables takes O(1)
  - parsing file takes O(n) where n is the number of characters in the input file \
  Total Big-O complexity is O(n).

# Project structure
The project uses SpringBoot with maven and JDK 17.
It is split into 4 different packages:
  - configuration
  - controllers
  - modules (which are the classes)
  - services:
    - impl (implementations)
    - interfaces

The resource folder is where the user has to copy the files that are to be read and parsed from the backend.

The current project has GitHub Workflow to fix formatting of the .java files to the Google Java code-style.

The main function `readFile()` does the parsing of the txt files using the following:
  - uses hashMap to store the found digits and their position.
  - tracks end of rows using `isEndOfLine()`. 
  - `isASCIIDigit()` checks whether the current character is digit if it is, then it adds the position to the hashMap
  - returns an async future result

# Dealing with HTML/XML Markup
1) One approach would be using regex to clear up markup.\
First, we would have to parse line by line since the markup tags might be in the beginning of the line and all the way at the end thus character by character parsing would not work. Optionally if the line is too big to be parsed into memory. We can again parse character by character but when we see the beginning of tag (e.g. `<s`) we start skipping characters till we see the ending of the tag (e.g. `>` or `/>`). 

The regex that we have to use is: `<[^>]*>`. It finds patterns that start with `<` have as many characters as needed and end with `>`. Then we would have to replace it like that - `replaceAll("<[^>]*>", "");`. This would remove the markup. Then we use the current algorithm.

2) Would be using a markup parsing library such as Jsoup and leave it to clean the markup.

# Build project
Open the project in IntelliJ and run the DemoApplication main.

# Future improvements to be implemented if the project is to be deployed:
  - Implement tests
  - Multi-threading
  - create CI/CD deployment
  - Dockerize application
  - Implement Controller function to upload a file instead of only parsing files already in the resources folder
