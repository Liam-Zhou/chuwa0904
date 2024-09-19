# Markdown Language Learning

Heading level 1
=====

Heading level 2
----

## Styling text
Bold: **This is bold text** and this  __is also bold text__

Italic: *This is italic text* and this text _is also italicized_

Strikethrough: ~~Mistaken text~~

Bold and nested italic: **This text is _extremely_ important!**

All bold and italic: ***All bold and italic***

Subscript: This is <sub>subscript</sub> text

Superscript: This is <sup>superscript</sup> text


## Quoting text
Not a quote text
> Quote text
### Nested quote text
> ## Quote text
> - Text
>> Nested quote text

## Quoting code
Some Git commands:
```
git status
git add
git command
```

## Supported color models
white: `#ffffff`, black: `#000000`

blue: `#0969DA` or `rgb(9, 105, 218)` or `hsl(212, 92%, 45%)`


## Links
Check out cute [puppies](https://www.four-paws.org/our-stories/publications-guides/10-tips-to-recognise-a-responsible-puppy-seller) here

### Section Links
### Relative Links


## Images
![Puppy pics](https://media.4-paws.org/1/e/d/6/1ed6da75afe37d82757142dc7c6633a532f53a7d/VIER%20PFOTEN_2019-03-15_001-2886x1999-1920x1330.jpg)



## Lists
### Unordered lists
- a
+ b
* c
### Ordered lists
1. a
2. b
3. c
### Nested lists
1. First list item
   - First nested item 
     - Second nested item
2. Second list item
   - First nested item
     - Second nested item
### Task lists
- [x] finished
- [ ] unfinished
- [ ] \(optional) unfinished


## Mention people and teams
@github/anonymous Happy Friday

## Emojis
Happy Friday :smiley:

## Footnotes
Here is the reference[^1].

[^1]: the reference.

## Alerts
> [!NOTE]
> Useful info

> [!TIP]
> 
> Helpful advice

> [!IMPORTANT]
> Key information

> [!WARNING]
> Urgent info


> [!CAUTION]
> Advises about risks or negative outcomes of certain actions


## Hiding contents with comments
<!-- hidden info in rendered markdown-->

## Ignoring markdown formatting
Tell Github to ignore Markdown formatting by using \ before Markdown character.

Let us rename \*new_name\* to \*old_name\*.