# Java Assignment
Assignment description:
  - extract numbers from input file.
  - provide a reference to them.

# Input
The input I have decided to be a txt file. The following is the content of the input.txt saved on the server:
```
123
1230
1239
8
```

If the user would like to query a file they would have to make a POST requst to localhost:8080 and pass as a params the filename as seen in this Postman request:
![2022-06-27-000907_1920x1080_scrot](https://user-images.githubusercontent.com/23415119/175835604-dda8b9dc-6a58-4433-9b64-15a4cd18308d.png)


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
The main function `readFile` does the parsing of the txt files using the following:
  - uses hashMap to store the found digits and their position.
  - tracks end of rows using `isEndOfLine`. 
  - `isASCIIDigit` checks whether the current character is digit if it is, then it adds the position to the hashMap
  - returns an async future task

Big-O complexity analysis:
  - creation of variables takes O(1)
  - parsing file takes O(n) where n is the number of characters in the input file

# Project structure
The project uses SpringBoot with maven and JDK 17.
It is split into 4 different packages:
  - configuration
  - controllers
  - modules (which are the classes)
  - services
    - impl (implementations)
    - interfaces
