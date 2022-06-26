# Java Assignment
Assignment description:
  - extract numbers from input file.
  - provide a reference to them.

# Input
The input I have decided to be a txt file. 
It can be of any size and even if the entire file is written on a single line it would be handled properly.

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
This way you would only see numbers that are part of the file and all their exact places.

# Performance
The main function `readFile` does the parsing of the txt files using the following:
    - uses hashMap to store the found digits and their position.
    - tracks end of rows using `isEndOfLine`. 
    - `isASCIIDigit` checks whether the current character is digit

