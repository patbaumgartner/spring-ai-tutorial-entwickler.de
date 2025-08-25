# Calling an AI with cURL

## Create your own API key on OpenAI

1. Go to OpenAI <https://platform.openai.com/signup>.
2. Sign up for an account.
3. Create an API key at <https://platform.openai.com/api-keys>.
4. Copy the API key to your clipboard.

Note: Your Open AI Platform account is the same as your ChatGPT account. If you create a new account and verify your phone number, you will be able to create an API key.

If your account is already exisiting and you are not eligble for the free quota, you can use the following API key:

```bash
export OPENAI_API_KEY=<YOUR_API_KEY>
```

### Modifying the bash script and calling OpenAI

1. Add `OPENAI_API_KEY` with your OpenAI API key as an environment variable.
2. Open the bash script `hello-openai.sh`.
3. Run the script as `./hello-openai.sh` in your terminal.
4. Check the output.
5. Modify your script if necessary.

Note: The API key is a secret and should not be shared.

Introduction and tutorials: <https://platform.openai.com/docs/introduction>
API Reference: <https://platform.openai.com/docs/api-reference>

## Creating your own API key with Anthropic Claude

1. Go to Anthropic Claude <https://claude.ai/>.
2. Sign up for an account.
3. Create an API key at <https://console.anthropic.com/dashboard>.
4. Copy the API key to the clipboard.

If your account is already exisiting and you are not eligble for the free quota, you can use the following API key:

```bash
export ANTHROPIC_API_KEY=<YOUR_API_KEY>
```

### Modify the bash script and call Claude

1. Add `ANTHROPIC_API_KEY` with your Anthropic Claude API key as an environment variable.
2. Open the bash script `hello-claude.sh`.
3. Run the script as `./hello-claude.sh` in your terminal.
4. Check the output.
5. Adjust your script if necessary.

Documentation: <https://docs.anthropic.com/en/home>

## Create your own API key on Google Gemini

1. Go to Google Gemini <https://ai.google.dev/aistudio/>.
2. Sign up for an account.
3. Create an API key at <https://aistudio.google.com/app/apikey>.
4. Copy the API key to the clipboard.

If your account is already exisiting and you are not eligble for the free quota, you can use the following API key:

```bash
export GOOGLE_API_KEY=<YOUR_API_KEY>
```

### Modify the bash script and call Gemini

1. Add `GOOGLE_API_KEY` with your Google Gemini API key as an environment variable.
2. Open the bash script `hello-gemini.sh`.
3. Run the script as `./hello-gemini.sh` in your terminal.
4. Check the output.
5. Modify your script if necessary.

Documentation: <https://ai.google.dev/gemini-api/docs>
Cookbook: <https://github.com/google-gemini/cookbook>
