# README

This is an exercise for anyone who wants to hone their skills while
dealing with legacy code. It was inspired by the excellent book *Working
Effectively With Legacy Code* by Michael Feathers.

## What is this?

Imagine that you are working at a company that tests medical equipment.
Tons of new equipment comes in every day, and tons of data is generated
by the lab technicians while experimenting on the equipment. To speed up
the data-input process, the company paid someone to make a simple
command-line tool to read a JSON file provided by the technicians and
then spit out that data to the terminal. This project is that tool.

The tool worked for a little while, but the technicians are having a
hard time typing everything in JSON and would like to be able to provide
data files in different formats. The people who use this tool wish that
there were more features, such as the ability to read nested experiments
and provide more specific output features, as well as bug fixes.

You have recently been hired to maintain this project, and are tasked
with adding new features and fixing bugs. The previous person who built
this is gone, so it is left to you to figure out how to do it.

## How does it work?

The tool works via a terminal. Simply build the jar and then run

```bash
$ java -jar <NAME_OF_JARFILE> <jsonfile>
```

to use it.

JSON files provided to the program must follow a specific format. They
must consist only of a single javascript object, with string keys and
values in the form of a list of integers. The file `file.json` in this
repo may be used as an example.

## What does the company want to add?

1. The tool only permits non-negative integers as inputs. The company wants
    * Negative numbers to be considered valid inputs
    * Floating point numbers to be considered valid inputs
    * The value `null` to be considered a valid input, and be distinct from 0
2. The tool needs to be able to accept XML as a valid input format
3. The tool needs to accept YAML as a valid input format
4. The tool needs to be able to have nested sets of numbers, like so:

```json
{
  "first": {
    "first-inside": {
      "first-first-inside": [...]
    }
    "second-inside": [...],
  }
  ...
}
```

