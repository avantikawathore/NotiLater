package service

internal object Prompts {

    fun summary(noteText: String): String = """
        Create a very short summary of the following notes.

        Return valid JSON with exactly these fields:
        {
          "summary": "One concise sentence explaining the main idea.",
          "keyPoints": [
            "First important point.",
            "Second important point."
          ]
        }

        Rules:
        - The summary must be one short sentence.
        - Generate 2 to 3 key points when the notes contain enough information.
        - Keep each key point under 15 words when possible.
        - Use simple, clear language.
        - Do not repeat the summary in the key points.
        - Do not add information that is not present in the notes.
        - If the note contains very little information, return fewer key points.
        - Return JSON only, without Markdown fences or extra commentary.

        Notes:
    """.trimIndent() + "\n" + noteText
}