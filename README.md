# ✉️ Email Reply Generator Extension

An AI-powered Chrome Extension that generates professional email replies using Google's Gemini API. Built with a Spring Boot backend, this tool enhances your Gmail productivity by injecting an "AI Reply" button directly into the Gmail interface.

## 🚀 Features

- 🔍 Automatically detects Gmail's Compose window
- 🧠 Uses Gemini API to generate context-aware replies
- 🛠️ Injects reply directly into Gmail's reply box
- 🎨 Option to choose different tones (e.g., Professional, Friendly)
- 💡 Built with Spring Boot for backend integration

  
## 🧩 Tech Stack

| Layer        | Technology                      |
|--------------|----------------------------------|
| Extension UI | JavaScript (Vanilla)            |
| Backend API  | Spring Boot (Java)              |
| AI Model     | Google Gemini API               |
| Target Site  | Gmail (via Content Scripts)     |

# How it works:
1. MutationObserver listens for Gmail’s Compose window.

2. Once detected, the extension injects an “AI Reply” button.

3. On click, it:

4. Extracts the original email content

5. Sends it to the Spring Boot backend

6. Backend uses Gemini API to generate a reply

7. Reply is inserted into Gmail’s compose box
