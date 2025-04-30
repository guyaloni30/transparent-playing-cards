# Card Matching Game - A Java-based Pattern Matching Card Game

This project implements a sophisticated card matching game where players need to find sets of cards (2 to 6 cards) that form valid patterns based on matching dots and circles with specific colors and positions. The game provides multiple matching modes and features an HTML visualization of the cards.

The game uses a unique card system where each card contains a colored dot and circle at specific coordinates. Cards can be rotated and matched based on the alignment of their dots and circles. The project supports various game modes from finding simple pairs to complex 6-card patterns, making it both challenging and engaging.

## Usage Instructions
### Prerequisites
- Java Development Kit (JDK) 24 or higher
- Maven 3.9.9 or higher
- A modern web browser (for HTML visualization)

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd cardsgame
```

2. Build the project with Maven:
```bash
mvn clean install
```

### Quick Start

1. Run a specific matching mode (e.g., for 2-card matching):
```bash
java -cp target/classes cardsgame.Match2
```

2. To view the cards visualization:
```bash
java -cp target/classes cardsgame.CardsHtml
```
Then open `cards.html` in your web browser.

### More Detailed Examples

1. Finding card pairs:
```java
// Create a new Match2 instance
Match2 matcher = new Match2();
// The matching results will be automatically computed and displayed
```

2. Finding 6-card patterns:
```java
// Create a new Match6 instance
Match6 matcher = new Match6();
// The matching results will be automatically computed and displayed
```

### Troubleshooting

Common issues and solutions:

1. Cards not displaying in browser:
   - Check if the HTML file was generated successfully
   - Verify your browser supports the CSS features used

2. Matching not working as expected:
   - Verify the card orientations are correct
   - Check if the dots and circles are properly aligned
   - Enable debug logging by modifying the Matcher class

## Data Flow
The game processes cards through a pattern matching pipeline that validates dot and circle positions and colors.

```ascii
[Card Input] -> [Pattern Detection] -> [Orientation Check] -> [Color Matching] -> [Position Validation] -> [Match Output]
     |                  |                    |                       |                    |
     v                  v                    v                       v                    v
[Card Data]    [Pattern Rules]     [Rotation Logic]         [Color Compare]    [Coordinate Check]
```

Key component interactions:
1. Card class manages the core card data and matching logic
2. ComparableCard handles card rotations and comparisons
3. Matcher subclasses implement specific pattern matching rules
4. Spot interface defines the common behavior for dots and circles
5. CardsHtml generates visual representation of cards
6. Color system provides consistent color management across components
7. Pattern matching validates position and color relationships between cards