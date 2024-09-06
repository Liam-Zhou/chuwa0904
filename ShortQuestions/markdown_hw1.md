# Markdown basic syntax
This manual outlined the basic syntax elements of Markdown.  

# Headings
To create a heading, add number signs (#) in front of a word or phrase. The number of number signs you use should correspond to the heading level. For example, to create a heading level three, use three number signs (e.g., ### My Header).  

    # Heading level 1
    ## Heading level 2
    ## Heading level 3  

The rendered output looks like this:  

# Heading level 1
## Heading level 2
## Heading level 3  

# Paragraphs
To create paragraphs, use a blank line to separate one or more lines of text.  

    I really like using Markdown.  
    I think I'll use it to format all of my documents from now on.  

The rendered output looks like this:  

I really like using Markdown.  
I think I'll use it to format all of my documents from now on.  

# Emphasis
You can add emphasis by making text bold or italic.

## Bold
To bold text, add two asterisks or underscores before and after a word or phrase. To bold the middle of a word for emphasis, add two asterisks without spaces around the letters.  

    I just love **bold text**.
    I just love __bold text__.
    Love**is**bold  

The rendered output looks like this:  

I just love **bold text**.  

I just love __bold text__.  

Love**is**bold  

## Italic
To italicize text, add one asterisk or underscore before and after a word or phrase. To italicize the middle of a word for emphasis, add one asterisk without spaces around the letters.  

        Italicized text is the *cat's meow*.
        Italicized text is the _cat's meow_.
        A*cat*meow  

The rendered output looks like this:  

Italicized text is the *cat's meow*.  

Italicized text is the _cat's meow_.  

A*cat*meow  

## Bold and Italic
To emphasize text with bold and italics at the same time, add three asterisks or underscores before and after a word or phrase. To bold and italicize the middle of a word for emphasis, add three asterisks without spaces around the letters.

        This text is ***really important***.
        This text is ___really important___.
        This text is __*really important*__.
        This text is **_really important_**.
        This is really***very***important text.  

The rendered output looks like this:  

This text is ***really important***.  

This text is ___really important___.  

This text is __*really important*__.  

This text is **_really important_**.  

This is really***very***important text.  

# Blockquotes
To create a blockquote, add a > in front of a paragraph.  

    > Dorothy followed her through many of the beautiful rooms in her castle.  

The rendered output looks like this:  

> Dorothy followed her through many of the beautiful rooms in her castle.  

## Blockquotes with Multiple Paragraphs  

    > Dorothy followed her through many of the beautiful rooms in her castle.
    >
    > The Witch bade her clean the pots and kettles and sweep the floor and keep the fire fed with wood.  

The rendered output looks like this:  

> Dorothy followed her through many of the beautiful rooms in her castle.
>
> The Witch bade her clean the pots and kettles and sweep the floor and keep the fire fed with wood.  

## Nested Blockquotes  

    > Dorothy followed her through many of the beautiful rooms in her castle.
    >
    >> The Witch bade her clean the pots and kettles and sweep the floor and keep the fire fed with wood.  

The rendered output looks like this:  

> Dorothy followed her through many of the beautiful rooms in her castle.
>
> The Witch bade her clean the pots and kettles and sweep the floor and keep the fire fed with wood.  

## Blockquotes with Other Elements  

    > #### The quarterly results look great!
    >
    > - Revenue was off the chart.
    > - Profits were higher than ever.
    >
    >  *Everything* is going according to **plan**.  

The rendered output looks like this:  

> #### The quarterly results look great!
>
> - Revenue was off the chart.
> - Profits were higher than ever.
>
>  *Everything* is going according to **plan**.  

# Lists
You can organize items into ordered and unordered lists.
## Ordered Lists  

To create an ordered list, add line items with numbers followed by periods. The numbers don’t have to be in numerical order, but the list should start with the number one.  

    1. First item
    2. Second item
    3. Third item
    4. Fourth item  

The rendered output looks like this:  

1. First item
2. Second item
3. Third item
4. Fourth item  

Another example with nested lists is as following. 

    1. First item
    2. Second item
    3. Third item
        1. Indented item
        2. Indented item
    4. Fourth item  
    
The rendered output looks like this:  

1. First item
2. Second item
3. Third item
    1. Indented item
    2. Indented item
4. Fourth item  

## Unordered Lists
To create an unordered list, add dashes (-), asterisks (*), or plus signs (+) in front of line items. Indent one or more items to create a nested list.  

    - First item
    - Second item
    - Third item
    - Fourth item  


    * First item
    * Second item
    * Third item
    * Fourth item  


    + First item
    + Second item
    + Third item
    + Fourth item  


    - First item
    - Second item
    - Third item
        - Indented item
        - Indented item
    - Fourth item  
    
The rendered output looks like this:  

- First item
- Second item
- Third item
- Fourth item  


* First item
* Second item
* Third item
* Fourth item  


+ First item
+ Second item
+ Third item
+ Fourth item  


- First item
- Second item
- Third item
    - Indented item
    - Indented item
- Fourth item  

## Starting Unordered List Items With Numbers
If you need to start an unordered list item with a number followed by a period, you can use a backslash `(\)` to escape the period.  

    - 1968\. A great year!
    - I think 1969 was second best.  

The rendered output looks like this:  

- 1968\. A great year!
- I think 1969 was second best.

# Code
To denote a word or phrase as code, enclose it in backticks `(``)`.
    At the command prompt, type `nano`.  

The rendered output looks like this:  

At the command prompt, type `nano`.  


## Escaping Backticks
If the word or phrase you want to denote as code includes one or more backticks, you can escape it by enclosing the word or phrase in double backticks `(``)`.  

    ``Use `code` in your Markdown file.``  

The rendered output looks like this:  

``Use `code` in your Markdown file.``  

## Code Blocks
To create code blocks, indent every line of the block by at least four spaces or one tab.  

    <html>
        <head>
        </head>
    </html>

# Links
To create a link, enclose the link text in brackets (e.g., [Duck Duck Go]) and then follow it immediately with the URL in parentheses (e.g., (https://duckduckgo.com)).

    My favorite search engine is [Duck Duck Go](https://duckduckgo.com).  

The rendered output looks like this:  

My favorite search engine is [Duck Duck Go](https://duckduckgo.com).  

# Images
To add an image, add an exclamation mark (!), followed by alt text in brackets, and the path or URL to the image asset in parentheses. You can optionally add a title in quotation marks after the path or URL.  

    ![The training model!](a.png "Training Model")  

The rendered output looks like this:  

![The training model!](a.png "Training Model")  
