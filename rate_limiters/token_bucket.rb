class TokenBucket
  attr_accessor :capacity, :tokens, :refill_rate, :last_request

  # capacity: tokens in bucket
  # seconds_to_refill: how many seconds it takes to refill the bucket
  def initialize(capacity, seconds_to_refill)
    self.capacity     = capacity
    self.tokens       = capacity
    self.refill_rate  = (1000 * seconds_to_refill.to_f/capacity) # convert to milli
    self.last_request = Time.now.to_f * 1000 # Use milli precision
  end

  def token
    current_time = Time.now.to_f * 1000

    # Check if we need to refill
    if @tokens < @capacity
      @tokens += ((current_time - @last_request) / @refill_rate).floor
      @tokens = [@tokens, @capacity].min
    end

    if @tokens > 0
      @tokens -= 1
      @last_request = current_time
      return true
    end
  end
end
