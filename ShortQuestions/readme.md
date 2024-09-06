# Markdown Example Homework

## Breaking NEWS (Overall Headers)
### N E W S (Sub header)
#### NEWS is breaking into 4 chars (Sub Sub header)

## Lists

### Unordered List
- Fruit
    - Apple
    - Orange
- Sport
    - Soccer
    - Footbal

### Ordered List
1. Star size
   1. Sun
   2. Others
2. Car size
    1. Bus
    2. Motor

## In text
*I'm Italicized*  
**I'm so Bold**  
~~I'm so Strikethrough~~

### Task List
- [x] Task 1: Write the Markdown structure.
- [ ] Task 2: Add complex elements.
- [ ] Task 3: Final review and submission.

## Links
Here is a link to [Google](https://www.google.com).

## Images
![Markdown Logo](https://markdown-here.com/img/icon128.png)

## Blockquote
> I'm a blockquote.

## Inline Code
I'm an inline code: `System.out.print("Hello, World!")`

## Code Block
```java
public static void main(String[] args){
    System.out.print("Hello World");
}
```

# Markdown Language Tutorials

## Headings

using '#' symbols before the heading text. Less '#' in use indicates higher level in heading.

## Styling text

Bold: \*\* ** ; **This is a bold text\*\*

Italic: \* * ; *This is an italicezed text\*

Bold and Italic: \*** \***; This text is **_really important_**

Strikethrough: ~~ ~~; ~~This was mistaken text~~

Subscript: \<sub> \</sub>; This is a <sub>subscript</sub> text.

Superscript: \<sup> \</sup>; This is a <sup>superscript</sup> text.

## Quoting text

Quote text with a \>

> Text that is quote

### Block quote with Multiple Paragraphs

Add a > on the blank lines between the paragraphs.

> Dorothy followed her through many of the beautiful rooms in her castle.
>
> The Witch bade her clean the pots and kettles and sweep the floor and keep the fire fed with wood.

### Nested Blockquotes

Add a >> in front of the paragraph you want to nest

> Dorothy followed her through many of the beautiful rooms in her castle.
>
> > The Witch bade her clean the pots and kettles and sweep the floor and keep the fire fed with wood.

### Quoting code

Use \` \` (backtick)  
Use `git status` to list all new or modified files that haven't yet been committed.

### Format code

Use \``` \``` Triple backticks

```
git status
git add
git commit
```

## Color models

Using \` Color models\`  
e.g.
HEX: `#0969DA`  
RGB: `rgb(9, 105, 218)`  
HSL: `hsl(212, 92%, 45%)`

## Links

Wrap link text in bracktes \[ \], then wrapping the URL in parentheses \( \)  
This site was build using [GitHub Pages](https://pages.github.com/).

relative links: relative link is a link that is relative to the current file/image.  
[Contribution guidelines for this project](../README.md).

## Images

display an image by adding \! and wrapping the alt text in [ ]. Alt text is a short text equivalent of the information in the image. Then, wrap the link for the image in parentheses ()  
![Screenshot of a comment on a GitHub issue showing an image, added in the Markdown, of an Octocat smiling and raising a tentacle.](https://myoctocat.com/assets/images/base-octocat.svg)

### Specifying the theme an image is shown to

By using the HTML <picture> element in combination with the prefers-color-scheme media feature.  
e.g. following code displays a sun image for light themes and a moon for dark themes:

```
<picture>
  <source media="(prefers-color-scheme: dark)" srcset="https://user-images.githubusercontent.com/25423296/163456776-7f95b81a-f1ed-45f7-b7ab-8fa810d529fa.png">
  <source media="(prefers-color-scheme: light)" srcset="https://user-images.githubusercontent.com/25423296/163456779-a8556205-d0a5-45e2-ac17-42d089e3c3f8.png">
  <img alt="Shows an illustrated sun in light mode and a moon with stars in dark mode." src="https://user-images.githubusercontent.com/25423296/163456779-a8556205-d0a5-45e2-ac17-42d089e3c3f8.png">
</picture>
```

<picture>
  <source media="(prefers-color-scheme: dark)" srcset="https://user-images.githubusercontent.com/25423296/163456776-7f95b81a-f1ed-45f7-b7ab-8fa810d529fa.png">
  <source media="(prefers-color-scheme: light)" srcset="https://user-images.githubusercontent.com/25423296/163456779-a8556205-d0a5-45e2-ac17-42d089e3c3f8.png">
  <img alt="Shows an illustrated sun in light mode and a moon with stars in dark mode." src="https://user-images.githubusercontent.com/25423296/163456779-a8556205-d0a5-45e2-ac17-42d089e3c3f8.png">
</picture>

## Lists

Make unordered list by preceding one or more lines of text with -, \*, or +.

- George Washington

* John Adams

- Thomas Jefferson

* Hello
* World

- Gone
- With
- The
- Wind

* Sam
* Houston

To order the list, preced each line with a number.

1. George H. W. Bush
2. George W. Bush
3. John Ellis Bush

### Nested Lists

Create a nested list by indenting one or more list items below another item.

1. First list item
    - First nested list item
        - Second nested list item

### Task lists

Using \- \[x\]

- [x] #739
- [ ] https://github.com/octo-org/octo-repo/issues/740
- [ ] Add delight to the experience when all tasks are complete :tada:

## Using Emojis

Typing `:EMOJICODE:`  
@octocat :+1: This PR looks great - it's ready to merge! :shipit:

## Footnotes

Using [^#]  
Here is a simple footnote[^1].

A footnote can also have multiple lines[^2].

[^1]: My reference.
[^2]:
To add line breaks within a footnote, prefix new lines with 2 spaces.
This is a second line.

## Comments

Using \<!-- This content will not appear in the rendered Markdown -->
