# Markdown Syntax Practice

## 1. Headers

Markdown supports 6 levels of headers.

```markdown
# Header 1
## Header 2
### Header 3
#### Header 4
##### Header 5
###### Header 6
```
## 2. Emphasis

Bold: **text** or __text__
Italic: *text* or _text_
Bold and Italic: ***text*** or ___text___
Strikethrough: ~~text~~

## 3. Lists

### Unordered List
- Item 1
  - Subitem 1.1
  - Subitem 1.2
- Item 2

### Ordered List
1. First item
2. Second item
   1. Subitem 2.1
   2. Subitem 2.2
3. Third item

## 4. Links
You can create links in two ways:
[Link Text](https://www.example.com)

Or with a title attribute:
[Link Text](https://www.example.com "Title Text")

## 5. Images
![Alt text](https://www.example.com/image.jpg "Optional Title")

## 6. Blockquotes

> This is a blockquote.
>> Nested blockquote.

## 7. Code

### Inline Code
Use `inline code` with backticks.

### Code Block
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

## 8. Escaping Characters

To use special characters without interpreting them as Markdown syntax, you can escape them with a backslash:

\*literal asterisks\*