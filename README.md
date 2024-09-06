# HW1 -- Summary of markdown

## Overview

> Markdown is a lightweight markup language with plain-text formatting syntax designed to make writing for the web easy. Created by John Gruber and Aaron Swartz in 2004, Markdown aims to be a simple and readable way to write structured documents. It allows writers to use plain text to format their content, which can then be converted to HTML or other formats.


## Headings

Markdown applications don’t agree on how to handle a missing space between the number signs (#) and the heading name. For compatibility, always **put a space between the number signs and the heading name**.

You should also put **blank lines** before and after a heading for compatibility.

```text
# Heading level 1
## Heading level 2
### Heading level 3
#### Heading level 4
##### Heading level 5
###### Heading level 6
```

## Styling text

```text
**bold**: ** ** or __ __
```
**This is bold text**

```text
*Italic*: * * or _ _
```
_This text is italicized_

```text
~~Strikethrough~~: ~~ ~~
```
~~This was mistaken text~~

```text
**Bold and _nested_ italic**: ** ** and _ _ 
```
**This text is _extremely_ important**

```text
***All bold and italic***: *** ***
```
***All this text is important***

```text
<sub>Subscript</sub>: <sub></sub>
```

This is a <sub>subscript</sub> text

```text
<sup>Superscript</sup>: <sup></sup>
```
This is a <sup>superscript</sup> text


## Quoting text

You can quote text with a `>`.

```text
> Text that is a quote
```

> Text that is a quote

## Blockquotes with Multiple Paragraphs

Blockquotes can contain multiple paragraphs. Add a `>` on the blank lines between the paragraphs.

```text
> Dorothy followed her through many of the beautiful rooms in her castle.
>
> The Witch bade her clean the pots and kettles and sweep the floor and keep the fire fed with 
```

> Dorothy followed her through many of the beautiful rooms in her castle.
>
> The Witch bade her clean the pots and kettles and sweep the floor and keep the fire fed with


## Nested Blockquotes

Blockquotes can be nested. Add a `>>` in front of the paragraph you want to nest.

```text
> Dorothy followed her through many of the beautiful rooms in her castle.
>
>> The Witch bade her clean the pots and kettles and sweep the floor and keep the fire fed with
```

> Dorothy followed her through many of the beautiful rooms in her castle.
>
>> The Witch bade her clean the pots and kettles and sweep the floor and keep the fire fed with

## Quoting code

You can call out code or a command within a sentence with single backticks. The text within the backticks will not be formatted.

```
Use `git status` to list all new or modified files that haven't yet been committed.
```

Use `git status` to list all new or modified files that haven't yet been committed.

## Links
You can create an inline link by wrapping link text in brackets `[ ]`, and then wrapping the URL in parentheses `( )`.

```
This site was built using [GitHub Pages](https://pages.github.com/).
```

This site was built using [GitHub Pages](https://pages.github.com/).

## Images

You can display an image by adding `!` and wrapping the alt text in `[ ]`. Alt text is a short text equivalent of the information in the image. Then, wrap the link for the image in parentheses `()`.

```
![Screenshot of a comment on a GitHub issue showing an image, added in the Markdown, of an Octocat smiling and raising a tentacle.](https://myoctocat.com/assets/images/base-octocat.svg)
```

![Screenshot of a comment on a GitHub issue showing an image, added in the Markdown, of an Octocat smiling and raising a tentacle.](https://myoctocat.com/assets/images/base-octocat.svg)

## Lists

You can make an unordered list by preceding one or more lines of text with `-`, `*`, or `+`.

```text
- George Washington
* John Adams
+ Thomas Jefferson
```

- George Washington
* John Adams
+ Thomas Jefferson

To order your list, precede each line with a number.

```text
1. James Madison
2. James Monroe
3. John Quincy Adams
```

1. James Madison
2. James Monroe
3. John Quincy Adams

## Nested Lists
You can create a nested list by indenting one or more list items below another item.

To create a nested list using the web editor on GitHub or a text editor that uses a monospaced font, like Visual Studio Code, you can align your list visually. Type space characters in front of your nested list item until the list marker character (`-` or `*`) lies directly below the first character of the text in the item above it.

```text
1. First list item
   - First nested list item
     - Second nested list item
```

1. First list item
   - First nested list item
     - Second nested list item


## Task lists
To create a task list, preface list items with a hyphen `-` and space followed by `[ ]`. To mark a task as complete, use `[x]`.

```text
- [x] #739
- [ ] https://github.com/octo-org/octo-repo/issues/740
- [ ] Add delight to the experience when all tasks are complete :tada:
```

- [x] #739
- [ ] https://github.com/octo-org/octo-repo/issues/740
- [ ] Add delight to the experience when all tasks are complete :tada:

If a task list item description begins with a parenthesis, you'll need to escape it with `\`:

```text
- [ ] \(Optional) Open a followup issue
```

- [ ] \(Optional) Open a followup issue

## Mentioning people and teams

You can mention a person or team on GitHub by typing `@` plus their username or team name. 

## Using emojis

You can add emoji to your writing by typing `:EMOJICODE:`, a colon followed by the name of the emoji.

```text
@octocat :+1: This PR looks great - it's ready to merge! :shipit:
```

@octocat :+1: This PR looks great - it's ready to merge! :shipit:

Typing `:` will bring up a list of suggested emoji. The list will filter as you type, so once you find the emoji you're looking for, press Tab or Enter to complete the highlighted result.

## Footnotes
You can add footnotes to your content by using this bracket syntax:

```text
Here is a simple footnote[^1].

A footnote can also have multiple lines[^2].

[^1]: My reference.
[^2]: To add line breaks within a footnote, prefix new lines with 2 spaces.
  This is a second line.
```

Here is a simple footnote[^1].

A footnote can also have multiple lines[^2].

[^1]: My reference.
[^2]: To add line breaks within a footnote, prefix new lines with 2 spaces.
This is a second line.

## Alerts

```text
> [!NOTE]
> Useful information that users should know, even when skimming content.

> [!TIP]
> Helpful advice for doing things better or more easily.

> [!IMPORTANT]
> Key information users need to know to achieve their goal.

> [!WARNING]
> Urgent info that needs immediate user attention to avoid problems.

> [!CAUTION]
> Advises about risks or negative outcomes of certain actions.
```

> [!NOTE]
> Useful information that users should know, even when skimming content.

> [!TIP]
> Helpful advice for doing things better or more easily.

> [!IMPORTANT]
> Key information users need to know to achieve their goal.

> [!WARNING]
> Urgent info that needs immediate user attention to avoid problems.

> [!CAUTION]
> Advises about risks or negative outcomes of certain actions.

## Hiding content with comments

```text
<!-- This content will not appear in the rendered Markdown -->
```

## URLs and Email Addresses

To quickly turn a URL or email address into a link, enclose it in angle brackets.

```text
<https://www.markdownguide.org>
<fake@example.com>
```

<https://www.markdownguide.org>
<fake@example.com>





