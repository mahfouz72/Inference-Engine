# Inference Engine for Logical Expressions

## Overview

This project involves designing and implementing an inference engine that can apply inference rules to logical expressions. The engine takes logical expressions as input, identifies applicable inference rules, and generates the corresponding output based on the rules' logic. The supported inference rules are Modus Ponens, Modus Tollens, Hypothetical Syllogism, Disjunctive Syllogism, and Resolution.

## Supported Inference Rules

1. **Modus Ponens:** Given expressions of the form "P > Q" and "P," the rule allows inferring "Q."

2. **Modus Tollens:** Given expressions of the form "P > Q" and "~Q," the rule allows inferring "~P."

3. **Hypothetical Syllogism:** Given expressions of the form "P > Q" and "Q > R," the rule allows inferring "P > R."

4. **Disjunctive Syllogism:** Given expressions of the form "P v Q" and "~P," the rule allows inferring "Q."

5. **Resolution:** Given expressions of the form "P v Q" and "~P v R," the rule allows inferring "Q v R."

## Implementation Details

The program will take two logical expressions as input and apply the inference rules to generate the output. The input expressions should be simple, without parentheses, and should contain at most one binary operation. 

The program will identify the type of inference rule that can be applied to the given input expressions and then perform the corresponding logical deduction. It will output the result of the inference process along with the applied rule.

## How to Use the Program

1. Compile and run the program.

2. Input two logical expressions, one per line, following the specified format.

3. The program will determine the applicable inference rule and generate the output.

4. The output will include the inferred expression and the name of the applied rule.

## Future Improvements

- Enhance the program to handle more complex expressions and support additional inference rules.
- Provide a user-friendly interface for input and output.
- Allow for the inclusion of parentheses for grouping expressions.
