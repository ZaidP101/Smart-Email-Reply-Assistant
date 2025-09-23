import {
  Box,
  CircularProgress,
  Container,
  FormControl,
  InputLabel,
  MenuItem,
  TextField,
  Typography,
  Select,
  Button,
  Paper,
} from '@mui/material';
import axios from 'axios';
import { useState } from 'react';

function App() {
  const [emailContent, setEmailContent] = useState('');
  const [tone, setTone] = useState('');
  const [rawReply, setRawReply] = useState('');
  const [generatedReply, setGeneratedReply] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handelSubmit = async () => {
    setLoading(true);
    setError('');
    try {
      const response = await axios.post('http://localhost:8080/api/email/generate', {
        emailContent,
        tone,
        rawReply,
      });
      setGeneratedReply(
        typeof response.data === 'string' ? response.data : JSON.stringify(response.data)
      );
    } catch (error) {
      setError('Failed to generate response, Try again');
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container maxWidth="md" sx={{ py: 4 }}>
      <Paper elevation={4} sx={{ p: 4, borderRadius: 3, bgcolor: 'background.paper' }}>
        <Typography variant="h4" component="h1" gutterBottom align="center" sx={{ color: 'primary.main' }}>
          Smart Email Reply Generator
        </Typography>

        <Box sx={{ mt: 3 }}>
          <TextField
            fullWidth
            multiline
            rows={8}
            variant="outlined"
            label="Original Email Content"
            value={emailContent}
            onChange={(e) => setEmailContent(e.target.value)}
            sx={{ mb: 3 }}
          />

          <FormControl fullWidth sx={{ mb: 3 }}>
            <InputLabel>Tone (Optional)</InputLabel>
            <Select
              value={tone || ''}
              label="Tone (Optional)"
              onChange={(e) => setTone(e.target.value)}
            >
              <MenuItem value="">None</MenuItem>
              <MenuItem value="Professional">Professional</MenuItem>
              <MenuItem value="Formal">Formal</MenuItem>
              <MenuItem value="Casual">Casual</MenuItem>
              <MenuItem value="Friendly">Friendly</MenuItem>
              <MenuItem value="Polite">Polite</MenuItem>
              <MenuItem value="Apologetic">Apologetic</MenuItem>
              <MenuItem value="Appreciative">Appreciative</MenuItem>
              <MenuItem value="Encouraging">Encouraging</MenuItem>
              <MenuItem value="Direct">Direct</MenuItem>
              <MenuItem value="Assertive">Assertive</MenuItem>
              <MenuItem value="Supportive">Supportive</MenuItem>
              <MenuItem value="Empathetic">Empathetic</MenuItem>
              <MenuItem value="Sarcastic">Sarcastic</MenuItem>
              <MenuItem value="Humorous">Humorous</MenuItem>
            </Select>
          </FormControl>

          <TextField
            fullWidth
            multiline
            rows={4}
            variant="outlined"
            label="Your Reply"
            placeholder="Type your raw response here..."
            value={rawReply}
            onChange={(e) => setRawReply(e.target.value)}
            sx={{ mb: 3 }}
          />

          <Button
            variant="contained"
            onClick={handelSubmit}
            disabled={loading || !emailContent}
            fullWidth
            sx={{ py: 1.5, fontWeight: 'bold' }}
          >
            {loading ? <CircularProgress size={24} color="inherit" /> : 'Generate Reply'}
          </Button>

          {error && (
            <Typography color="error" sx={{ mt: 2 }}>
              {error}
            </Typography>
          )}
        </Box>

        {generatedReply && (
          <Box sx={{ mt: 4 }}>
            <Typography variant="h6" gutterBottom>
              Generated Reply
            </Typography>
            <TextField
              fullWidth
              multiline
              rows={9}
              variant="outlined"
              value={generatedReply}
              inputProps={{ readOnly: true }}
            />
            <Button
              variant="outlined"
              sx={{ mt: 2 }}
              onClick={() => navigator.clipboard.writeText(generatedReply)}
            >
              Copy Response
            </Button>
          </Box>
        )}
      </Paper>
    </Container>
  );
}

export default App;
