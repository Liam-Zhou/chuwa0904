# Markdown Syntax

## Heading

Use `#` for Heading level 1

Use `##` for Heading level 2

### Heading Note

Same Pattern md can render text from h1 to h6 (from `#` to `#######`)

## Paragraph

Use `<p></p>` or `a bland line` for paragraphs:

<p>I really like using Markdown.</p>

## Lines

Use `<br>` or `2 or more spaces` in end of line to create a line break:

New Line<br>
Another Line

## Text Styling

Use `** **` or `__ __` for text bold: **bold**

Use `* *` or `_ _` for text bold: _Italic_

Use `~~ ~~` for text bold: ~~Strikethrough~~

Use `** _ _ **` for bold and nested italic: **Bold and _nested_ italic**

Use `*** ***` for all bold and italic: **_Bold and nested italic_**

Use `<sub></sub>` for all bold and italic: AAA<sub>FFF</sub>AAA

Use `<sup></sup>` for all bold and italic: AAA<sup>FFF</sup>AAA

## Quote

Use `>` for quoting text (can quote multiple oaragraphs):

> Text that is a quote
>
> anther paragrah that is a quote

Use `>>` for Nested paragraphs quote:

> Text that is a quote
>
> > anther nested paragrah that is a quote

Quote can include other Markdown elements (for example `*** ***`, `-` etc):

> #### The quarterly results look great!
>
> - Revenue was off the chart.
> - Profits were higher than ever.
>
> - _Everything_ is going according to **plan**.

## List

Use numbers `1.` `2.` `3.` etc for ordered list

1. First item
2. Second item
3. Third item
   1. Indented item
   2. Indented item
4. Fourth item

Use `-` or `*` or `+` or `-` for ordered list

- First item
- Second item
- Third item
  - Indented item
  - Indented item
- Fourth item

## Code

Use ` `` ` to quote code within a block: First Coding `Hello World` Sentence

use ` ``` ``` ` to quote entire block code:

```
git status
git add
git commit
```

## Horizontal Rules

Use `---`, `***` , or `___` on a line by themselves to quote code within a block:

---

## Links

Use parentheses with url after brackets `[Duck Duck Go](https://duckduckgo.com)`: Go to [Website](https://duckduckgo.com).

Use `<>` to turn a URL or email address into a link:

- <https://www.markdownguide.org>
- <fake@example.com>

Link can be used with markdown elements, for example:

- **[EFF](https://eff.org)**.
- _[Markdown Guide](https://www.markdownguide.org)_.
- [`code`](#code).

## Images

- Use `!` and wrap the alt text in `[ ]` and wrap the link for the image in `()` after `[]`:

  `![Miao!](https://myoctocat.com/assets/images/base-octocat.svg)`

  ![Miao!](https://myoctocat.com/assets/images/base-octocat.svg)

- Link Images

  enclose the Markdown for the image in brackets, and then add the link in parentheses

  `[![Miao!](https://myoctocat.com/assets/images/base-octocat.svg)](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#images)`

  [![Miao!](https://myoctocat.com/assets/images/base-octocat.svg)](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#images)

## HTML in markdown

Many Markdown applications allow you to use HTML tags in Markdown-formatted text. For example `<em></em>`:  
This **word** is bold. This <em>word</em> is italic.

# Github Useful Markdown Usage

## Task Lists

use `[]` for task lists:

- [x] #739
- [ ] https://github.com/octo-org/octo-repo/issues/740
- [ ] Add delight to the experience when all tasks are complete :tada:

## Mentioning people and teams

use `@` to mention:

@github/support What do you think about these updates?

## Emojis

Use `:EMOJICODE:` to use emojis: :+1:

## Alerts

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

## Footnotes

Add footnotes to your content by using this bracket syntax:

```
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
